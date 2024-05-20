import os
import shutil

def generate_files(ids: list[str]):
    templates = {
        "casing": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "casing_cast": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "head": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "head_cast": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "pellet": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "pellet_cast": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """,

        "unfinished": """
{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/cartridges/{id}/{id}_{moduleId}"
    }
}
        """
    }

    os.makedirs("output", exist_ok=True)
    for cartridgeId in ids:
        for file_type, template in templates.items():
            file_name = f"output/{cartridgeId}_{file_type}.json"
            content = template.replace("{id}", cartridgeId).replace("{moduleId}", file_type)
            with open(file_name, "w") as file:
                file.write(content)
            if file_name.__contains__("cast"):
                texture_file = f"sources/textures/cartridges/casts/general_{file_type}.png"
                shutil.copy2(texture_file, f"output")
                os.rename(f"output/general_{file_type}.png", f"output/{cartridgeId}_{file_type}.png")


if __name__ == "__main__":
    ids = input("Enter a list of IDs separated by spaces: ").split()
    generate_files(ids)
