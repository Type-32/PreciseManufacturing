import os


def generate_files(ids: list[str]):
    templates = {
        "_casing": """{
  "type": "create:filling",
  "ingredients": [
    {
      "item": "prma:{id}_casing_cast"
    },
    {
      "fluid": "prma:molten_basalt_infused_iron",
      "nbt": {},
      "amount": 90
    }
  ],
  "results": [
    {
      "item": "prma:{id}_casing"
    }
  ]
}""",

        "_casing_cast": """{
  "type": "create:cutting",
  "ingredients": [
    {
      "item": "minecraft:iron_ingot"
    }
  ],
  "results": [
    {
      "item": "prma:{id}_casing_cast",
      "count": 3
    }
  ],
  "processingTime": 280
}""",

        "_head": """{
  "type": "create:filling",
  "ingredients": [
    {
      "item": "prma:{id}_head_cast"
    },
    {
      "fluid": "prma:molten_copper",
      "nbt": {},
      "amount": 60
    }
  ],
  "results": [
    {
      "item": "prma:{id}_head"
    }
  ]
}""",

        "_head_cast":
            """{
  "type": "create:cutting",
  "ingredients": [
    {
      "item": "minecraft:iron_ingot"
    }
  ],
  "results": [
    {
      "item": "prma:{id}_head_cast",
      "count": 5
    }
  ],
  "processingTime": 280
}""",

        "":
            """{
  "type": "create:sequenced_assembly",
  "ingredient": {
    "item": "prma:{id}_casing"
  },
  "transitionalItem": {
    "item": "prma:{id}_unfinished"
  },
  "sequence": [
    {
      "type": "create:deploying",
      "ingredients": [
        {
          "item": "prma:{id}_unfinished"
        },
        {
          "item": "minecraft:iron_nugget"
        }
      ],
      "results": [
        {
          "item": "prma:{id}_unfinished"
        }
      ]
    },
    {
      "type": "create:deploying",
      "ingredients": [
        {
          "item": "prma:{id}_unfinished"
        },
        {
          "item": "minecraft:gunpowder"
        }
      ],
      "results": [
        {
          "item": "prma:{id}_unfinished"
        }
      ]
    },
    {
      "type": "create:deploying",
      "ingredients": [
        {
          "item": "prma:{id}_unfinished"
        },
        {
          "item": "prma:{id}_head"
        }
      ],
      "results": [
        {
          "item": "prma:{id}_unfinished"
        }
      ]
    },
    {
      "type": "create:pressing",
      "ingredients": [
        {
          "item": "prma:{id}_unfinished"
        }
      ],
      "results": [
        {
          "item": "prma:{id}_unfinished"
        }
      ]
    }
  ],
  "results": [
    {
      "item": "tacz:ammo",
      "nbt": {
        "AmmoId": "tacz:{id}"
      },
      "chance": 96.0
    },
    {
      "item": "prma:{id}_casing",
      "chance": 4.0
    }
  ],
  "loops": 1
}"""
    }
    for cartridgeId in ids:
        for file_type, template in templates.items():
            file_name = f"output/{cartridgeId}{file_type}.json"
            content = template.replace("{id}", cartridgeId)
            with open(file_name, "w") as file:
                file.write(content)


if __name__ == "__main__":
    ids = input("Enter a list of IDs separated by spaces: ").split()
    generate_files(ids)
