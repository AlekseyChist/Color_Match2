package com.example.color_match2

object BaseColors {
    val colors = listOf(
        Color("Красный", "#FF0000"),
        Color("Темно-красный", "#8B0000"),
        Color("Розовый", "#FFC0CB"),
        Color("Малиновый", "#DC143C"),
        Color("Коралловый", "#FF7F50"),
        Color("Оранжевый", "#FFA500"),
        Color("Темно-оранжевый", "#FF8C00"),
        Color("Золотой", "#FFD700"),
        Color("Желтый", "#FFFF00"),
        Color("Лимонный", "#FFFACD"),
        Color("Светло-желтый", "#FFFFE0"),
        Color("Зеленый", "#008000"),
        Color("Лайм", "#00FF00"),
        Color("Темно-зеленый", "#006400"),
        Color("Оливковый", "#808000"),
        Color("Бирюзовый", "#40E0D0"),
        Color("Аква", "#00FFFF"),
        Color("Голубой", "#87CEEB"),
        Color("Синий", "#0000FF"),
        Color("Темно-синий", "#00008B"),
        Color("Индиго", "#4B0082"),
        Color("Фиолетовый", "#8A2BE2"),
        Color("Пурпурный", "#800080"),
        Color("Лавандовый", "#E6E6FA"),
        Color("Белый", "#FFFFFF"),
        Color("Серый", "#808080"),
        Color("Серебряный", "#C0C0C0"),
        Color("Черный", "#000000"),
        Color("Коричневый", "#A52A2A"),
        Color("Бежевый", "#F5F5DC")
    )

    fun getHexCodeForColor(colorName: String): String {
        return colors.find { it.name == colorName }?.hexCode ?: "#000000"
    }
}