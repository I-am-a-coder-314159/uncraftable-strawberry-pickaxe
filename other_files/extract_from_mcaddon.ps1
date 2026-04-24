# PowerShell script to extract and merge .mcaddon files into behavior_pack and resource_pack
# Usage: Run from the project root directory
# Supports various .mcaddon folder structures (Strawberry Minecraft, standard behavior_pack/resource_pack, etc.)

# Define paths
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$projectRoot = Split-Path -Parent (Split-Path -Parent $scriptDir)
$mcaddonSourceDir = Join-Path $scriptDir "mcaddon_files"
$zipOutputDir = Join-Path $scriptDir "zip_files"
$extractedDir = Join-Path $scriptDir "extracted_zip"
$processedDir = Join-Path $scriptDir "processed_mcaddons"
$behaviorPackDest = Join-Path $projectRoot "behavior_pack"
$resourcePackDest = Join-Path $projectRoot "resource_pack"

# Create directories if they don't exist
@($zipOutputDir, $extractedDir, $processedDir) | ForEach-Object {
    if (-not (Test-Path $_)) {
        New-Item -ItemType Directory -Path $_ -Force | Out-Null
        Write-Host "Created directory: $_"
    }
}

# Function to merge directories without overwriting
function Merge-DirectoriesNonDestructive {
    param (
        [string]$SourceDir,
        [string]$DestDir
    )
    
    if (-not (Test-Path $SourceDir)) {
        Write-Host "Source directory does not exist: $SourceDir"
        return
    }
    
    # Ensure destination exists
    if (-not (Test-Path $DestDir)) {
        New-Item -ItemType Directory -Path $DestDir -Force | Out-Null
    }
    
    # Get all items from source
    $items = Get-ChildItem -Path $SourceDir -Force
    foreach ($item in $items) {
        $destPath = Join-Path $DestDir $item.Name
        
        if ($item.PSIsContainer) {
            # It's a directory - merge recursively
            Merge-DirectoriesNonDestructive -SourceDir $item.FullName -DestDir $destPath
        }
        else {
            # It's a file - copy only if it doesn't exist
            if (-not (Test-Path $destPath)) {
                Copy-Item -Path $item.FullName -Destination $destPath -Force
                Write-Host "  Copied: $($item.Name)"
            }
            else {
                Write-Host "  Skipped (exists): $($item.Name)"
            }
        }
    }
    
    <#
    foreach ($item in $items) {
        $destPath = Join-Path $DestDir $item.Name
        
        if ($item.PSIsContainer) {
            # It's a directory - merge recursively
            if (-not (Test-Path $destPath)) {
                New-Item -ItemType Directory -Path $destPath -Force | Out-Null
            }
            Merge-DirectoriesNonDestructive -SourceDir $item.FullName -DestDir $destPath
        }
        else {
            # It's a file - copy only if it doesn't exist
            if (-not (Test-Path $destPath)) {
                Copy-Item -Path $item.FullName -Destination $destPath -Force
                Write-Host "  Copied: $($item.Name)"
            }
            else {
                Write-Host "  Skipped (exists): $($item.Name)"
            }
        }
    }#>
}

# Main processing loop
$mcaddonFiles = Get-ChildItem -Path $mcaddonSourceDir -Filter "*.mcaddon" -ErrorAction SilentlyContinue

if ($mcaddonFiles.Count -eq 0) {
    Write-Host "No .mcaddon files found in $mcaddonSourceDir"
    exit
}

Write-Host "Found $($mcaddonFiles.Count) .mcaddon file(s) to process"

foreach ($mcaddon in $mcaddonFiles) {
    $mcaddonName = $mcaddon.BaseName
    Write-Host "Processing: $($mcaddon.Name)"
    
    # Step 1: Copy and rename to .zip
    $zipFileName = "$mcaddonName.zip"
    $zipPath = Join-Path $zipOutputDir $zipFileName
    
    if (-not (Test-Path $zipPath)) {
        Copy-Item -Path $mcaddon.FullName -Destination $zipPath
        Write-Host "  Copied to zip_files as: $zipFileName"
    }
    else {
        Write-Host "  .zip already exists, skipping copy"
    }
    
    # Step 2: Extract the .zip
    $extractPath = Join-Path $extractedDir $mcaddonName
    
    if (-not (Test-Path $extractPath)) {
        Expand-Archive -Path $zipPath -DestinationPath $extractPath
        Write-Host "  Extracted to: extracted_zip\$mcaddonName"
    }
    else {
        Write-Host "  Already extracted, skipping extraction"
    }
    
    # Step 3: Merge behavior_pack
    # Try multiple possible folder names for behavior pack
    $behaviorFolderNames = @("Strawberry Minecraft Behavior", "Strawberry Minecraft #1 Behavior", "behavior_pack", "Behavior Pack")
    $sourceBehavior = $null
    foreach ($folderName in $behaviorFolderNames) {
        $potentialPath = Join-Path $extractPath $folderName
        if (Test-Path $potentialPath) {
            $sourceBehavior = $potentialPath
            break
        }
    }
    
    if ($sourceBehavior) {
        Write-Host "  → Merging behavior_pack:"
        Merge-DirectoriesNonDestructive -SourceDir $sourceBehavior -DestDir $behaviorPackDest
    }
    else {
        Write-Host "  → No behavior pack folder found in .mcaddon"
    }
    
    # Step 4: Merge resource_pack
    # Try multiple possible folder names for resource pack
    $resourceFolderNames = @("Strawberry Minecraft Resources", "Strawberry Minecraft #1 Resources", "resource_pack", "Resource Pack")
    $sourceResource = $null
    foreach ($folderName in $resourceFolderNames) {
        $potentialPath = Join-Path $extractPath $folderName
        if (Test-Path $potentialPath) {
            $sourceResource = $potentialPath
            break
        }
    }
    
    if ($sourceResource) {
        Write-Host "  → Merging resource_pack:"
        Merge-DirectoriesNonDestructive -SourceDir $sourceResource -DestDir $resourcePackDest
    }
    else {
        Write-Host "  → No resource pack folder found in .mcaddon"
    }
    
    # Step 5: Mark as processed
    $processedMarkerPath = Join-Path $processedDir "$mcaddonName.processed"
    $mcaddonProcessedPath = Join-Path $mcaddonSourceDir "$($mcaddon.Name).processed"
    
    # Create marker files
    New-Item -Path $processedMarkerPath -ItemType File -Force | Out-Null
    New-Item -Path $mcaddonProcessedPath -ItemType File -Force | Out-Null
    
    Write-Host "  Marked as processed"
}

Write-Host "Script completed successfully!"
Write-Host "Summary:"
Write-Host "  - Copied .mcaddon files to: zip_files"
Write-Host "  - Extracted to: extracted_zip"
Write-Host "  - Merged into: behavior_pack and resource_pack"
Write-Host "  - Processed markers created to prevent re-processing"
