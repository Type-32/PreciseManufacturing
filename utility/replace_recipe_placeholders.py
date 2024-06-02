import os
import fileinput


def replace_in_files(directory, old_str, new_str):
    for filename in os.listdir(directory):
        if os.path.isfile(os.path.join(directory, filename)):
            with fileinput.FileInput(os.path.join(directory, filename), inplace=True) as file:
                for line in file:
                    print(line.replace(old_str, new_str), end='')


# Use the function
replace_in_files('/../src/generated/resources/data/prma/recipes', 'prma:ammo_placeholder', 'tacz:ammo')
replace_in_files('/../src/generated/resources/data/prma/recipes', 'prma:modern_kinetic_gun_placeholder', 'tacz:modern_kinetic_gun')
