import os

folder_path = "item"
old_string = "moreusefulcopper"
new_string = "more_useful_copper"

def replace_string_in_file(file_path, old_string, new_string):
    with open(file_path, 'r') as file:
        content = file.read()

    updated_content = content.replace(old_string, new_string)

    with open(file_path, 'w') as file:
        file.write(updated_content)

    print(f'Replaced in file: {file_path}')

def process_files_in_folder(folder_path, old_string, new_string):
    for filename in os.listdir(folder_path):
        file_path = os.path.join(folder_path, filename)

        if os.path.isfile(file_path):
            replace_string_in_file(file_path, old_string, new_string)

if __name__ == "__main__":
    process_files_in_folder(folder_path, old_string, new_string)
