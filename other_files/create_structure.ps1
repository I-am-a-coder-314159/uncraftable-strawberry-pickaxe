param (
    [Parameter(Mandatory=$true)]
    [int]$FileNumber,

    [bool]$FromStrawberries = $true
)

function New-FileStructure {
    param (
        [Parameter(Mandatory=$true)]
        [ValidateSet("block", "entity", "item")]
        [string]$Type,

        [Parameter(Mandatory=$true)]
        [string]$Name,

        [string]$ModelPath = $null
    )
    
        $FilePath = Read-Host 'Enter file path for file number ' + $i
        Get-Item -Path $FilePath -ErrorAction {Write-Host "Invalid path. Please enter a valid file path." -ForegroundColor Red; New-FileStructure}
        if ($Type -eq "block" -or $Type -eq "entity") {
            if ($null -eq $ModelPath) {
                $ModelPath = Read-Host 'Enter model path for file number ' + $i
                if ($ModelPath -eq "" -and $Type -eq "block") {
                    Write-Host "No model path provided. Skipping model file copy." -ForegroundColor Yellow
                    $ModelPath = $false
                }
            }
            if ($ModelPath -ne $false) {
                Get-Item -Path $ModelPath -ErrorAction {Write-Host "Invalid path. Please enter a valid model file path." -ForegroundColor Red; New-FileStructure}
            }
      }
        $CodeName = $Name -replace " ", "_"
        if ($Type -eq "block") {
            Read-Host "Seconds to destroy:" -OutVariable SecondsToDestroy
            Read-Host "Light emission (0-15):" -OutVariable LightEmission
            Read-Host "Friction (0-1):" -OutVariable Friction
            Write-Host "Creating block: $Name main BP JSON file. Go to the new created JSON file to edit block attributes and components." -ForegroundColor Green
            New-Item -ItemType File -Path "behavior_pack\blocks\$CodeName.json" -Force -Value "
{
  `"format_version`": `"1.20.20`",
  `"minecraft:block`": {
    `"description`": {
      `"identifier`": `"strawberrymc:$CodeName`",
    },
    `"components`": {

      `"minecraft:destructible_by_mining`": {
        "seconds_to_destroy": $SecondsToDestroy
      },
      `"minecraft:light_emission`": $LightEmission,
      `"minecraft:loot`": `"loot_tables/blocks/$CodeName.json`",
      `"minecraft:map_color`": `"#ff0000`",
      `"minecraft:friction`": 0.6
    }
  }
}
            
            
            "
            Copy-Item -Path $FilePath -Destination "resource_pack\textures\blocks\$(Split-Path -Path $FilePath -Leaf)" -Force
            if ($ModelPath -ne $false) {
                Copy-Item -Path $ModelPath -Destination "resource_pack\models\blocks\$(Split-Path -Path $ModelPath -Leaf)" -Force
            }
        }

}

if ($FromStrawberries -and $FileNumber -eq 0) {$Auto = $true}
else {$Auto = $false}

if ($Auto) {} else {
    foreach ($i in 0..$FileNumber) {
        New-FileStructure
    }
}