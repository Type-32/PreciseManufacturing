import os
import shutil


def copy_json_files(src_folder, dst_folder):
    # Create the destination folder if it doesn't exist
    if not os.path.exists(dst_folder):
        os.makedirs(dst_folder)

    # Iterate over all files and folders in the source folder
    for root, dirs, files in os.walk(src_folder):
        for file in files:
            # Check if the file has a .json extension
            if file.endswith(".json"):
                src_file = os.path.join(root, file)
                dst_file = os.path.join(dst_folder, file)

                # Copy the .json file to the destination folder
                shutil.copy2(src_file, dst_file)
                print(f"Copied: {src_file} -> {dst_file}")


# Prompt the user for the source and destination folder paths
# src_folder = input("Enter the source folder path: ")
# dst_folder = input("Enter the destination folder path: ")

# Call the function to copy the .json files
copy_json_files("../src/main/resources/assets/prma/models/item/cartridges",
                "../src/generated/resources/assets/prma/models/item")
copy_json_files("../src/main/resources/assets/prma/models/item/weapons",
                "../src/generated/resources/assets/prma/models/item")
