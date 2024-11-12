package com.example.PersistanceTutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class PersistanceTutorialApplication

fun main(args: Array<String>) {
	runApplication<PersistanceTutorialApplication>(*args)
}

interface PlayerDataRepository: JpaRepository<playerdata, Long>
{

}

@RestController
@RequestMapping("api")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository)
{
	@GetMapping("PlayerData")
	fun GetAll() = PlayerDataRepo.findAll()

	@PostMapping("PlayerData")
	fun SavePlayerData(@RequestBody PlayerData: playerdata)
	{
		PlayerDataRepo.save(PlayerData)
	}

}

@Entity
class playerdata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)