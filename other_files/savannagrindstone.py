import sys

usermode = input('''
Welcome to the Block/Item Handler!
    Press B to create a block
    Press I to create an item
    (Entities are not supported yet)

''')
isblock = True if str(usermode).lower == 'b' else False
isitem = True if str(usermode).lower == 'i' else False

if isblock:
    if input("Complex Block?").lower() == 'no':
        displayname = input("Enter block display name: ")
        blockid = input("Enter block ID: ")
        blockvar = str(blockid).upper()
        blockitemvar = str(blockid).upper() + "_ITEM"
        destroytimef = input("Enter time in seconds to destroy (with fist): ")
        explosionresistance = input("Enter blast resistance: ")
        new_reg_code = f'''
        public static final DeferredBlock<Block> {blockvar} = BLOCKS.registerSimpleBlock(
                "{blockid}",
                BlockBehaviour.Properties.of().destroyTime({destroytimef}f).explosionResistance({explosionresistance}f)
        )

        public static final DeferredItem<BlockItem> {blockitemvar} = ITEMS.registerSimpleBlockItem(
                {blockvar}
        )

        '''
    else:
        print("complex blocks are not yet supported, ask aadi on how to do this kinda stuff")
        sys.exit(2) # error code 2: human error

    creativetab = input("""
Creative tabs:
1. Building blocks
2. Natural blocks
3. Colored blocks
4. Functional blocks
5. Redstone blocks
6. tools and utilities
7. combat
8. food and drink
9. ingredients
10. spawn eggs
11. operator blocks
choose one: """)
    try:
        creativetab = int(creativetab) - 1
        
    except TypeError:
        print('not a valid option. sorry.')
        sys.exit(2)
    
#Updating Registration.java
with open("java\\src\\main\\java\\com\\nobigcorps\\strawberrymc\\Registration.java", "r") as reg:
    reglines = reg.readlines()

for index in range(len(reglines) - 1, -1, -1):
    if "public static void init(IEventBus modEventBus)" in reglines[index]:
        reglines.insert(index, new_reg_code)
        break

with open("java\\src\\main\\java\\com\\nobigcorps\\strawberrymc\\Registration.java", "w") as file:
    file.writelines(reglines)

