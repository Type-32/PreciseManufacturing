import os
import shutil
import json

# RifleModuleBuilder.py
from typing import List

from enum import Enum


class RifleModuleType(Enum):
    LOWER_RECEIVER = "lower_receiver"
    UPPER_RECEIVER = "upper_receiver"
    LONG_BODY = "long_body"
    HANDGUARD = "handguard"
    STOCK = "stock"
    MAGAZINE = "magazine"
    BARREL = "barrel"
    GRIP = "grip"
    TRIGGER = "trigger"
    FIRE_CONTROL_GROUP = "fire_control_group"
    PUMP = "pump"
    FIRE_SELECTOR = "fire_selector"
    CARTRIDGE_WELL = "cartridge_well"
    SHELL_TUBE = "shell_tube"
    BULLPUP_BODY = "bullpup_body",
    RECEIVER = "receiver",
    BOLT = "bolt"

    def __str__(self):
        return self.value


def parse_input_modules() -> list[RifleModuleType]:
    seq: int = 1
    for modules in RifleModuleType:
        print(f"{seq} - {modules.name}")
        seq += 1
    inp: str = input("Select Modules via Number(s) (Separate your input with spaces): ")

    seq = 1
    result: list[RifleModuleType] = []
    indexes = inp.split()
    if not inp.__contains__("/"):
        for modules in RifleModuleType:
            for index in indexes:
                if int(index) == seq and not result.__contains__(modules):
                    result.append(modules)

    if inp.__contains__("/standard_rifle"):
        result = [
            RifleModuleType.GRIP,
            RifleModuleType.LOWER_RECEIVER,
            RifleModuleType.UPPER_RECEIVER,
            RifleModuleType.HANDGUARD,
            RifleModuleType.BARREL,
            RifleModuleType.MAGAZINE,
            RifleModuleType.FIRE_CONTROL_GROUP,
            RifleModuleType.FIRE_SELECTOR,
            RifleModuleType.TRIGGER,
            RifleModuleType.STOCK
        ]
    elif inp.__contains__("/bullpup_rifle"):
        result = [
            RifleModuleType.GRIP,
            RifleModuleType.BULLPUP_BODY,
            RifleModuleType.MAGAZINE,
            RifleModuleType.FIRE_CONTROL_GROUP,
            RifleModuleType.GRIP,
            RifleModuleType.TRIGGER,
            RifleModuleType.FIRE_SELECTOR
        ]
    elif inp.__contains__("/pump_action"):
        result = [
            RifleModuleType.RECEIVER,
            RifleModuleType.SHELL_TUBE,
            RifleModuleType.BARREL,
            RifleModuleType.TRIGGER,
            RifleModuleType.PUMP,
            RifleModuleType.FIRE_CONTROL_GROUP,
            RifleModuleType.TRIGGER,
            RifleModuleType.STOCK
        ]
    elif inp.__contains("/bolt_action"):
        result = [
            RifleModuleType.BOLT,
            RifleModuleType.BARREL,
            RifleModuleType.LONG_BODY,
            RifleModuleType.TRIGGER,
            RifleModuleType.STOCK,
            RifleModuleType.CARTRIDGE_WELL,
            RifleModuleType.GRIP
        ]

    return result


def generate_files(id: str, modules: list[RifleModuleType]):
    templates = {
        "modules": ["",
                    """{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/weapons/guns/{id}/modules/{id}_{moduleId}"
    }
}"""],

        "blueprints": ["_blueprint",
                       """{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/weapons/guns/{id}/blueprints/{id}_{moduleId}_blueprint"
    }
}"""],

        "casts": ["_cast",
                  """{
    "parent": "item/generated",
    "textures": {
        "layer0": "prma:item/weapons/guns/{id}/casts/{id}_{moduleId}_cast"
    }
}"""]
    }

    try:
        os.makedirs("output", exist_ok=True)
        os.makedirs("output/models", exist_ok=True)
        os.makedirs("output/textures", exist_ok=True)
        os.makedirs("output/models/casts", exist_ok=True)
        os.makedirs("output/models/modules", exist_ok=True)
        os.makedirs("output/models/blueprints", exist_ok=True)
        os.makedirs("output/textures/casts", exist_ok=True)
        os.makedirs("output/textures/modules", exist_ok=True)
        os.makedirs("output/textures/blueprints", exist_ok=True)
    except:
        print("Folder Already exists, skipping folder creation")
    finally:
        for module in modules:
            for file_type, template in templates.items():
                file_name = f"output/models/{file_type}/{id}_{module}{template[0]}.json"
                content = template[1].replace("{id}", id).replace("{moduleId}", module.__str__())
                with open(file_name, "w") as file:
                    file.write(content)
                texture_file = f"sources/textures/guns/{file_type}/general_{module}{template[0]}.png"
                shutil.copy2(texture_file, f"output/textures/{file_type}")
                os.rename(f"output/textures/{file_type}/general_{module}{template[0]}.png",
                          f"output/textures/{file_type}/{id}_{module}{template[0]}.png")


def generate_translation_keys(item_id: str, module_types: list[RifleModuleType]):
    try:
        os.makedirs("output", exist_ok=True)
        os.makedirs("output/lang", exist_ok=True)
    except:
        print("Folder Already exists, skipping folder creation")
    finally:
        translations = {}

        for module_type in module_types:
            key_base = f"item.prma.{item_id}_{module_type.value}"
            value_base = f"{item_id.upper()} {module_type.value.replace('_', ' ').title()}"

            translations[key_base] = value_base
            translations[f"{key_base}_blueprint"] = f"{value_base} Blueprint"
            translations[f"{key_base}_cast"] = f"{value_base} Cast"

        with open(f"output/lang/{item_id.upper()} Translations.json", "w") as file:
            file.write(json.dumps(translations))

        return translations


def generate_cast_cutting_recipes(item_id: str, modules: list[RifleModuleType]):
    templates = {
        "cutting": """{
  "type": "create:cutting",
  "ingredients": [
    {
      "item": "create:iron_sheet"
    }
  ],
  "results": [
    {
      "item": "prma:{id}_{moduleId}_cast",
      "count": 1
    }
  ],
  "processingTime": 400
}"""
    }

    try:
        os.makedirs(f"output/recipes/cutting/{id}", exist_ok=True)
    except:
        print("Folder Already exists, skipping folder creation")
    finally:
        for module in modules:
            for file_type, template in templates.items():
                file_name = f"output/recipes/{file_type}/{id}/{id}_{module}_cast.json"
                content = template.replace("{id}", id).replace("{moduleId}", module.__str__())
                with open(file_name, "w") as file:
                    file.write(content)


def generate_decomponentalizing_recipes(item_id: str, modules: list[RifleModuleType]):
    templates = {
        "decomponentalizing": """{
  "type": "prma:decomponentalizing",
  "ingredient": {
    "item": "tacz:modern_kinetic_gun",
    "nbt":{
      "GunId": "tacz:{id}"
    }
  },
  "results": {
    "item": "prma:{id}_{moduleId}_blueprint"
  },
  "processingTime": 2500
}"""
    }

    try:
        os.makedirs(f"output/recipes/decomponentalizing/{item_id}", exist_ok=True)
    except:
        print("Folder Already exists, skipping folder creation")
    finally:
        for module in modules:
            for file_type, template in templates.items():
                file_name = f"output/recipes/{file_type}/{item_id}/{item_id}_{module}_blueprint.json"
                content = template.replace("{id}", item_id).replace("{moduleId}", module.__str__())
                with open(file_name, "w") as file:
                    file.write(content)


def generate_filling_recipes(item_id: str, modules: list[RifleModuleType]):
    templates = {
        "barrel": """{
  "type": "create:filling",
  "ingredients": [
    {
      "item": "prma:{id}_{moduleId}_cast"
    },
    {
      "fluid": "prma:molten_basalt_infused_iron",
      "nbt": {},
      "amount": 250
    }
  ],
  "results": [
    {
      "item": "prma:{id}_{moduleId}"
    }
  ]
}"""
    }

    try:
        os.makedirs(f"output/recipes/filling/{item_id}", exist_ok=True)
    except:
        print("Folder Already exists, skipping folder creation")
    finally:
        for module in modules:
            for file_type, template in templates.items():
                file_name = f"output/recipes/filling/{item_id}/{item_id}_{module}.json"
                content = template.replace("{id}", item_id).replace("{moduleId}", module.__str__())
                with open(file_name, "w") as file:
                    file.write(content)


if __name__ == "__main__":
    id: str = input("Enter a gun Id: ")
    modules: list[RifleModuleType] = parse_input_modules()

    # generate_files(id, modules)
    # generate_translation_keys(id, modules)
    # generate_cast_cutting_recipes(id, modules)
    # generate_filling_recipes(id, modules)
    generate_decomponentalizing_recipes(id, modules)
