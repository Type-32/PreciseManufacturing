let MOD = (domain, id, x) => (x ? `${x}x ` : "") + (id.startsWith('#') ? '#' : "") + domain + ":" + id.replace('#', '')
let PRMA = (id, x) => MOD("prma", id, x)
let MC = (id, x) => MOD("minecraft", id, x)

ServerEvents.recipes(event => {
    tweaks(event)
})

function tweaks(event){
    event.remove({ id: 'prma:mixing/small_ammunition_gunpowder_mixing'})
    event.remove({ id: 'prma:mixing/medium_ammunition_gunpowder_mixing'})
    event.remove({ id: 'prma:mixing/long_ammunition_gunpowder_mixing'})

    event.recipes.createCompacting([PRMA('small_ammunition_gunpowder')], [
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        PRMA("flint_powder"),
        PRMA("flint_powder"),
        PRMA("flint_powder")
    ])

    event.recipes.createCompacting([PRMA('medium_ammunition_gunpowder')], [
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        PRMA("raw_sulfur_powder"),
        PRMA("raw_sulfur_powder"),
        PRMA("raw_sulfur_powder"),
        PRMA("raw_sulfur_powder"),
        PRMA("raw_sulfur_powder")
    ])

    event.recipes.createCompacting([PRMA('long_ammunition_gunpowder')], [
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        MC("gunpowder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder"),
        PRMA("sulfur_powder")
    ])
}