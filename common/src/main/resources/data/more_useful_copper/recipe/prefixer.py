import os

def add_prefix_to_filenames(directory, prefix):
    # Iterate through all files in the directory
    for filename in os.listdir(directory):
        file_path = os.path.join(directory, filename)

        # Check if the path is a file (not a directory)
        if os.path.isfile(file_path):
            # Create a new filename with the prefix
            new_filename = f"{prefix}{filename}"

            # Rename the file with the new filename
            new_file_path = os.path.join(directory, new_filename)
            os.rename(file_path, new_file_path)

            print(f"Renamed: {filename} to {new_filename}")

def main():
    directory_path = "temp"
    prefix = "more_useful_copper"

    add_prefix_to_filenames(directory_path, prefix)

if __name__ == "__main__":
    main()
