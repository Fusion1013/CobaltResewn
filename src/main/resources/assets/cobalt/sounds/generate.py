import os
import pathlib
import json

directory = pathlib.Path(__file__).parent.resolve()

sound_dict = {}

def search_folder(fpath, fname: str, prefix: str):
    print("Searching folder '" + fname + "'")

    if (prefix != ""): sound_dict[prefix[1:]] = {'sounds': []}

    for entry in os.scandir(fpath):
        if (os.path.isdir(entry.path)):
            search_folder(entry.path, entry.path, prefix + "." + entry.name)
        elif (os.path.isfile(entry.path)):
            if (entry.name.endswith('.ogg')):
                process_file(entry, prefix + "." + entry.name.replace(".ogg", ""), prefix)

def process_file(entry: os.DirEntry, id: str, folder_id: str):
    print("Added file '" + entry.name + "'")
    
    real_id = "cobalt:" + id[1:].replace('.', '/')

    sound_dict[id[1:]] = {'sounds': [real_id]}
    if (folder_id != ""):
        sound_dict[folder_id[1:]]['sounds'].append({"name": real_id})

search_folder(directory, "sounds", "")

with open(os.path.join(directory.parent, 'sounds.json'), 'w') as outfile:
    json.dump(sound_dict, outfile, indent=4)
    print(os.path.abspath(outfile.name))