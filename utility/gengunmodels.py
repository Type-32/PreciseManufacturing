import os

# RifleModuleBuilder.py
from typing import List

from enum import Enum


class RifleModuleType(Enum):
    LOWER_RECEIVER = "lower_receiver"
    UPPER_RECEIVER = "upper_receiver"
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
    BULLPUP_BODY = "bullpup_body"

    def __str__(self):
        return self.value


class RifleModuleBuilder:
    def __init__(self, *modules: RifleModuleType):
        self.modules: list[RifleModuleType] = list(modules)

    def __init__(self, modules: list[RifleModuleType]):
        self.modules = modules.copy()

    def get(self) -> list[RifleModuleType]:
        return self.modules.copy()

    def add(self, module: RifleModuleType) -> 'RifleModuleBuilder':
        self.modules.append(module)
        return RifleModuleBuilder(self.modules)

    def remove(self, module: RifleModuleType) -> 'RifleModuleBuilder':
        self.modules.remove(module)
        return RifleModuleBuilder(self.modules)

    def __copy__(self):
        return RifleModuleBuilder(self.modules)


def generate_files(id: str, modules: list[RifleModuleType]):
    templates = {
        "module": ["", """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/weapons/guns/{id}/{id}_{moduleId}"
            }
        }
        """],

        "unfinished": ["_unfinished", """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/weapons/guns/{id}_{moduleId}_unfinished"
            }
        }
        """],

        "cast": ["_cast", """
        {
            "parent": "item/generated",
            "textures": {
                "layer0": "prma:item/casts/weapons/guns/{id}_{moduleId}_cast"
            }
        }
        """]
    }

    for module in modules:
        for file_type, template in templates.items():
            file_name = f"output/{id}_{module}{template[0]}"
            content = template[1].replace("{id}", id).replace("{moduleId}", module)
            with open(file_name, "w") as file:
                file.write(content)


if __name__ == "__main__":
    id = input("Enter a gun Id: ")
    generate_files(id)
