package terraria.model

sealed abstract class DropWeapon(val name:String, val dmg: Int, val model: String) {
}
class Sword(name: String, dmg: Int, model: String) extends DropWeapon(name,dmg,model)
class Bow(name: String, dmg: Int, model: String) extends DropWeapon(name,dmg,model)
class Dildo(name: String, dmg: Int, model: String) extends DropWeapon(name,dmg,model)
