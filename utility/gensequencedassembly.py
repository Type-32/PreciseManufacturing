from enum import Enum
import json


class CreateRecipeTypes(Enum):
    SequencedAssembly = "create:sequenced_assembly",
    Mixing = "create:mixing",
    Milling = "create:milling",
    Filling = "create:filling",
    Emptying = "create:emptying",
    Cutting = "create:cutting",
    Crushing = "create:crushing",
    Compacting = "create:compacting",
    MechanicalCrafting = "create:mechanical_crafting",
    Deploying = "create:deploying",
    Haunting = "create:haunting",
    ItemApplication = "create:item_application",
    Pressing = "create:pressing",
    Splashing = "create:splashing"


class Item:
    def __init__(self, item: str, nbt: object = None, chance: float = None):
        self.chance = chance
        self.item = item
        self.nbt = nbt

    def set_entry(self, namespace: str, name: str):
        self.item = f"{namespace}:{name}"
        return self

    def set_nbt(self, obj: object = None):
        self.nbt = obj
        return self

    def set_chance(self, perc: float = None):
        self.chance = perc
        return self

    def to_json(self) -> str:
        return json.dumps(self)


class Fluid(Item):
    def __init__(self, fluid: str, amount: int):
        super().__init__(None, None, None)
        self.fluid = fluid
        self.amount = amount


class Assembly:
    def __init__(self, type: CreateRecipeTypes, ingredients: list[Item], results: list[Item]):
        self.results = results
        self.ingredients = ingredients
        self.type = type

    def add_ingredient(self, item: Item):
        self.ingredients.append(item)
        return self

    def remove_ingredient(self, index: int):
        self.ingredients.pop(index)
        return self

    def add_result(self, item: Item):
        self.ingredients.append(item)
        return self

    def remove_result(self, index: int):
        self.ingredients.pop(index)
        return self

    def to_json(self) -> str:
        return json.dumps(self)


class SequencedAssembly:
    def __init__(self, ingredient: Item, transitionalItem: Item, sequence: list[Assembly], results: list[Item],
                 loops: int):
        self.results = results
        self.sequence = sequence
        self.transitionalItem = transitionalItem
        self.ingredient = ingredient
        self.loops = loops
        self.type = CreateRecipeTypes.SequencedAssembly

    def __init__(self, ingredient: Item, transitionalItem: Item, loops: int):
        self.results = []
        self.sequence = []
        self.transitionalItem = transitionalItem
        self.ingredient = ingredient
        self.loops = loops
        self.type = CreateRecipeTypes.SequencedAssembly

    def add_sequence(self, assembly: Assembly):
        self.sequence.append(assembly)
        return self

    def remove_sequence(self, index: int):
        self.sequence.pop(index)
        return self

    def add_result(self, item: Item):
        self.results.append(item)
        return self

    def remove_result(self, index: int):
        self.results.pop(index)
        return self

    def set_ingredient(self, item: Item):
        self.ingredient = item
        return self

    def set_transitional_item(self, item: Item):
        self.transitionalItem = item
        return self

    def set_loop(self, times: int):
        self.loops = times
        return self

    def to_json(self) -> str:
        return json.dumps(self)


def dashed_line(content: str = ""):
    print(f"-----{content}-----")


def retrieve_recipe_type() -> CreateRecipeTypes:
    count: int = 1
    for type in CreateRecipeTypes:
        print(f"{count} - {type.name}")

    return CreateRecipeTypes(int(input("Enter Recipe Type Index")))


def retrieve_item_entries() -> list[Item]:
    result: list[Item] = []
    for i in range(int(input("Number of Item Entries: "))):
        result.append(Item(
            input("Enter Entry: "),
            object(input("Enter NBT: ")),
            float(input("Chances: "))
        ))

    return result


if __name__ == "__main__":
    file_name = input("File Name: ") + ".json"
    recipe = SequencedAssembly(
        Item(input("Ingredient Entry (i.e. minecraft:bucket): ")),
        Item(input("Transitional Item Entry (i.e. minecraft:bucket): ")),
        int(input("Loop Times: "))
    )
    processes: int = int(input("Processes of Assembly: "))
    for i in range(processes):
        dashed_line(f"Assembly {i+1}/{processes}")
        recipe.add_sequence(Assembly(retrieve_recipe_type(), retrieve_item_entries(), retrieve_item_entries()))

    finals: int = int(input("Number of Resulting Items: "))
    for i in range(finals):
        dashed_line(f"Result Items {i+1}/{finals}")
        recipe.results = retrieve_item_entries()

    with open(file_name, "w") as file:
        file.write(recipe.to_json())
