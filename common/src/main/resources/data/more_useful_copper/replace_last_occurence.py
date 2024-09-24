import os

def replace_last_occurrence(file_path, search_text, replace_text):
    with open(file_path, 'r') as file:
        content = file.read()

    # Find the last occurrence of the search text
    last_occurrence = content.rfind(search_text)

    if last_occurrence != -1:
        # Replace the last occurrence of the search text
        content = content[:last_occurrence] + content[last_occurrence:].replace(search_text, replace_text, 1)

        # Write the modified content back to the file
        with open(file_path, 'w') as file:
            file.write(content)

def process_folder(folder_path):
    for filename in os.listdir(folder_path):
        file_path = os.path.join(folder_path, filename)

        # Check if it's a file
        if os.path.isfile(file_path):
            replace_last_occurrence(file_path, 'item', 'id')

# Set the path to the "recipes" folder
folder_path = 'recipe'

# Process each file in the folder
process_folder(folder_path)
