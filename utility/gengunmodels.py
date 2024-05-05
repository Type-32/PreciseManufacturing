import os


def generate_files(ids: list[str]):
    templates = {
        "casing": """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/bullets/casing/{id}"
            }
        }
        """,

        "casing_cast": """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/casts/casing/general_casing_cast"
            }
        }
        """,

        "head": """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/bullets/head/{id}"
            }
        }
        """,

        "head_cast": """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/casts/head/general_head_cast"
            }
        }
        """,

        "unfinished": """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/bullets/unprimed/{id}"
            }
        }
        """
    }

    for gunId in ids:
        for file_type, template in templates.items():
            file_name = f"output/{gunId}_{file_type}.json"
            content = template.replace("{id}", gunId)
            with open(file_name, "w") as file:
                file.write(content)


if __name__ == "__main__":
    ids = input("Enter a list of IDs separated by spaces: ").split()
    generate_files(ids)
