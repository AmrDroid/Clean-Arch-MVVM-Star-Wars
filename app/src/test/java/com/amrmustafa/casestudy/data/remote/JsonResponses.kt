package com.amrmustafa.casestudy.data.remote.resources

val CHARACTER_DETAILS = """
{
    "name": "Chewbacca", 
    "height": "228", 
    "mass": "112", 
    "hair_color": "brown", 
    "skin_color": "unknown", 
    "eye_color": "blue", 
    "birth_year": "200BBY", 
    "gender": "male", 
    "homeworld": "http://swapi.dev/api/planets/1/", 
    "films": [
        "http://swapi.dev/api/films/1/"
    ], 
    "species": [
        "http://swapi.dev/api/species/1/"
    ], 
    "vehicles": [
        "http://swapi.dev/api/vehicles/1/"
    ], 
    "starships": [
        "http://swapi.dev/api/starships/1/", 
        "http://swapi.dev/api/starships/1/"
    ], 
    "created": "2014-12-10T16:42:45.066000Z", 
    "edited": "2014-12-20T21:17:50.332000Z", 
    "url": "http://swapi.dev/api/people/1/"
}
""".trimIndent()

val CHARACTER_FILMS = """
{
    "name": "Chewbacca", 
    "height": "228", 
    "mass": "112", 
    "hair_color": "brown", 
    "skin_color": "unknown", 
    "eye_color": "blue", 
    "birth_year": "200BBY", 
    "gender": "male", 
    "homeworld": "http://swapi.dev/api/planets/1/", 
    "films": [
        "http://swapi.dev/api/films/1/"
    ], 
    "species": [
        "http://swapi.dev/api/species/1/"
    ], 
    "vehicles": [
        "http://swapi.dev/api/vehicles/1/"
    ], 
    "starships": [
        "http://swapi.dev/api/starships/1/"
            ], 
    "created": "2014-12-10T16:42:45.066000Z", 
    "edited": "2014-12-20T21:17:50.332000Z", 
    "url": "http://swapi.dev/api/people/1/"
}
""".trimIndent()


val PLANET = """
{
    "name": "Kashyyyk", 
    "rotation_period": "26", 
    "orbital_period": "381", 
    "diameter": "12765", 
    "climate": "tropical", 
    "gravity": "1 standard", 
    "terrain": "jungle, forests, lakes, rivers", 
    "surface_water": "60", 
    "population": "45000000", 
    "residents": [
        "http://swapi.dev/api/people/1/"
    ], 
    "films": [
        "http://swapi.dev/api/films/1/"
    ], 
    "created": "2014-12-10T13:32:00.124000Z", 
    "edited": "2014-12-20T20:58:18.442000Z", 
    "url": "http://swapi.dev/api/planets/14/"
}
""".trimIndent()

val SEARCH_RESULT = """
{
  "count": 1,
  "next": null,
  "previous": null,
  "results": [
    {
      "name": "Darth Vader",
      "height": "202",
      "mass": "136",
      "hair_color": "none",
      "skin_color": "white",
      "eye_color": "yellow",
      "birth_year": "41.9BBY",
      "gender": "male",
      "homeworld": "/api/planets/1/",
      "films": [
        "/api/films/1/"
      ],
      "species": [
        "/api/species/1/"
      ],
      "vehicles": [],
      "starships": [
        "/api/starships/1/"
      ],
      "created": "2014-12-10T15:18:20.704000Z",
      "edited": "2014-12-20T21:17:50.313000Z",
      "url": "/api/people/1/"
    }
  ]
}
""".trimIndent()


val NO_SEARCH_RESULT = """
    {
      "count": 0,
      "next": null,
      "previous": null,
      "results": []
    }
""".trimIndent()


val SPECIES = """
{
    "name": "Wookie", 
    "classification": "mammal", 
    "designation": "sentient", 
    "average_height": "210", 
    "skin_colors": "gray", 
    "hair_colors": "black, brown", 
    "eye_colors": "blue, green, yellow, brown, golden, red", 
    "average_lifespan": "400", 
    "homeworld": "http://swapi.dev/api/planets/1/", 
    "language": "Shyriiwook", 
    "people": [
        "http://swapi.dev/api/people/1/"
    ], 
    "films": [
        "http://swapi.dev/api/films/1/"
    ], 
    "created": "2014-12-10T16:44:31.486000Z", 
    "edited": "2014-12-20T21:36:42.142000Z", 
    "url": "http://swapi.dev/api/species/3/"
}
""".trimIndent()


val NOT_FOUND = """
    {
      "detail": "Not found"
    }
""".trimIndent()


