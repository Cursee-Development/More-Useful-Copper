import os

folder_path = "temp"
old_substring = "acacia"
new_substring = "copper"

def rename_files(folder_path, old_substring, new_substring):
    for filename in os.listdir(folder_path):
        if old_substring in filename:
            new_filename = filename.replace(old_substring, new_substring)
            old_filepath = os.path.join(folder_path, filename)
            new_filepath = os.path.join(folder_path, new_filename)

            # Check if the new filename already exists
            if os.path.exists(new_filepath):
                os.remove(new_filepath)
                print(f'Deleted existing file: {new_filename}')

            os.rename(old_filepath, new_filepath)
            print(f'Renamed: {filename} -> {new_filename}')

if __name__ == "__main__":
    rename_files(folder_path, old_substring, new_substring)


# import os
#
# folder_path = "recipes"
# old_substring = "_brick_"
# new_substring = "_bricks_"
#
# def rename_files(folder_path, old_substring, new_substring):
#     for filename in os.listdir(folder_path):
#         if old_substring in filename:
#             new_filename = filename.replace(old_substring, new_substring)
#             old_filepath = os.path.join(folder_path, filename)
#             new_filepath = os.path.join(folder_path, new_filename)
#
#             # Check if the new filename already exists
#             if os.path.exists(new_filepath):
#                 print(f'Error: File "{new_filename}" already exists. Skipping.')
#             else:
#                 os.rename(old_filepath, new_filepath)
#                 print(f'Renamed: {filename} -> {new_filename}')
#
# if __name__ == "__main__":
#     rename_files(folder_path, old_substring, new_substring)


# import os
#
# folder_path = "recipes"
# old_substring = "_brick_"
# new_substring = "_bricks_"
#
# def rename_files(folder_path, old_substring, new_substring):
#     for filename in os.listdir(folder_path):
#         if old_substring in filename:
#             new_filename = filename.replace(old_substring, new_substring)
#             old_filepath = os.path.join(folder_path, filename)
#             new_filepath = os.path.join(folder_path, new_filename)
#             os.rename(old_filepath, new_filepath)
#             print(f'Renamed: {filename} -> {new_filename}')
#
# if __name__ == "__main__":
#     rename_files(folder_path, old_substring, new_substring)
