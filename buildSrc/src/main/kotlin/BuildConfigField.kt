object BuildConfigField {

    object Types {
    }

    fun escapeCharacter(value: String): String {
        return "\"$value\""
    }

}