package strop

fun main(args: Array<String>) {
    val availableWeapons = weapons.lines().map { Item.from(it) }.sortedBy { it.cost }
    val availableArmor = armor.lines().map { Item.from(it) }.sortedBy { it.cost }
    val availableRings = rings.lines().map { Item.from(it) }.sortedBy { it.cost }

    var maxCost = Int.MIN_VALUE

    for (pickedWeapon in availableWeapons) {
        for (pickedArmor in availableArmor) {
            for (firstRing in availableRings) {
                for (secondRing in availableRings - firstRing) {
                    val me = equipPlayer(100, pickedWeapon, pickedArmor, firstRing, secondRing)
                    val cost = arrayOf(pickedWeapon, pickedArmor, firstRing, secondRing).sumBy(Item::cost)

                    val boss = Player(hitPoints = 109, damage = 8, armor = 2)

                    val winner = fight(me, boss)
                    if (winner != me) {
                        maxCost = Math.max(maxCost, cost)
                    }
                }
            }
        }
    }

    if (maxCost > Int.MIN_VALUE) {
        println(maxCost)
        return
    }
}

private fun equipPlayer(hitPoints: Int, weapon: Item, armor: Item, firstRing: Item, secondRing: Item): Player {
    val totalDamage = weapon.damage + firstRing.damage + secondRing.damage
    val totalArmor = armor.armor + firstRing.armor + secondRing.armor
    return Player(hitPoints, totalDamage, totalArmor)
}

private fun fight(player1: Player, player2: Player): Player {
    var attacker = player1
    var defender = player2

    while (player1.hitPoints > 0 && player2.hitPoints > 0) {
        val damageDealt = Math.max(1, attacker.damage - defender.armor)
        defender.hitPoints -= damageDealt

        // switch roles
        val tmp = attacker
        attacker = defender
        defender = tmp
    }

    return if (player1.hitPoints <= 0) player2 else player1
}

private interface Item {
    val name: String
    val cost: Int
    val damage: Int
    val armor: Int

    companion object {
        fun from(line: String): Item {
            val parts = line.split("\\s{2,}".toRegex())
            return ItemImpl(parts[0], parts[1].toInt(), parts[2].toInt(), parts[3].toInt())
        }
    }

    private class ItemImpl(override val name: String,
                           override val cost: Int,
                           override val damage: Int,
                           override val armor: Int) : Item
}

private data class Player(var hitPoints: Int, val damage: Int, val armor: Int)

const val weapons = """Dagger        8     4       0
Shortsword   10     5       0
Warhammer    25     6       0
Longsword    40     7       0
Greataxe     74     8       0"""

const val armor = """Leather      13     0       1
Chainmail    31     0       2
Splintmail   53     0       3
Bandedmail   75     0       4
Platemail   102     0       5
None          0     0       0"""

const val rings = """Damage +1    25     1       0
Damage +2    50     2       0
Damage +3   100     3       0
Defense +1   20     0       1
Defense +2   40     0       2
Defense +3   80     0       3
None1         0     0       0
None2         0     0       0"""
