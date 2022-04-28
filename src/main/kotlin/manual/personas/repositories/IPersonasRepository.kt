package manual.personas.repositories

import dagger.personas.models.Persona
import dagger.personas.repositories.CrudRepository
import java.util.*

interface IPersonasRepository : CrudRepository<Persona, UUID>