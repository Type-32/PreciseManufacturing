import os
import fileinput

def replace_in_files(directory, old_str, new_str):
    if not os.path.exists(directory):
        print(f"Directory {directory} does not exist")
        return

    for filename in os.listdir(directory):
        file_path = os.path.join(directory, filename)

        if os.path.isfile(file_path):
            print(f"Processing file: {file_path}")

            try:
                with fileinput.FileInput(file_path, inplace=True, backup='.bak') as file:
                    for line in file:
                        print(line.replace(old_str, new_str), end='')
                print(f"Replaced '{old_str}' with '{new_str}' in {file_path}")

                # Delete the backup file
                backup_file = file_path + '.bak'
                if os.path.exists(backup_file):
                    os.remove(backup_file)
                    print(f"Deleted backup file: {backup_file}")

                # Delete the double backup file
                double_backup_file = file_path + '.bak.bak'
                if os.path.exists(double_backup_file):
                    os.remove(double_backup_file)
                    print(f"Deleted double backup file: {double_backup_file}")

            except Exception as e:
                print(f"Error processing file {file_path}: {e}")
        elif os.path.isdir(file_path):
            print(f"Entering directory: {file_path}")
            replace_in_files(file_path, old_str, new_str)  # Correct recursive call

# Use the function

try:
    files = os.listdir('D:/OD/Windows Defaults/Documents/Idea Projects/PreciseManufacturing/src/generated/resources/data/prma/recipes')
    print(f"Files in directory: {files}")
except Exception as e:
    print(f"Error listing directory: {e}")

replace_in_files('D:/OD/Windows Defaults/Documents/Idea Projects/PreciseManufacturing/src/generated/resources/data/prma/recipes', 'prma:ammo_placeholder', 'tacz:ammo')
replace_in_files('D:/OD/Windows Defaults/Documents/Idea Projects/PreciseManufacturing/src/generated/resources/data/prma/recipes', 'prma:modern_kinetic_gun_placeholder', 'tacz:modern_kinetic_gun')
