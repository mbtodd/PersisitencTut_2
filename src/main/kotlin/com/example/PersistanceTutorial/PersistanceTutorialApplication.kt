package com.example.PersistanceTutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class PersistanceTutorialApplication

fun main(args: Array<String>) {
	runApplication<PersistanceTutorialApplication>(*args)
}

interface PlayerDataRepository: JpaRepository<playerdata, Long>
{
	@Query(value = "Call GetByPID(:PID)", nativeQuery = true)
	fun GetByPID(@Param("PID") PID: Int): playerdata
}

interface CubeDataRepository: JpaRepository<cubedata, Long>
{

}

@RestController
@RequestMapping("api")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository)
{
	@GetMapping("PlayerData")
	fun GetAll() = PlayerDataRepo.findAll()

	@GetMapping("PlayerData/{PID}")
	fun GetPID(@PathVariable(value = "PID") PID: Int) : playerdata
	{
		var PData = PlayerDataRepo.GetByPID(PID)
		return PData;
	}

	@PostMapping("PlayerData")
	fun SavePlayerData(@RequestBody PlayerData: playerdata)
	{
		PlayerDataRepo.save(PlayerData)
	}
}

// CUBE CONTROLLER
@RestController
@RequestMapping("api")
class CubeDataRestController(val CubeDataRepo: CubeDataRepository)
{
	@GetMapping("CubeData")
	fun GetAll() = CubeDataRepo.findAll()

	@PostMapping("CubeData")
	fun SaveCubeData(@RequestBody CubeData: cubedata)
	{
		CubeDataRepo.save(CubeData)
	}

}

// API Call
@Entity
class playerdata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var isvalid: Boolean = false, var pid: Int = -1,var health: Float = 100.0f, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)

@Entity
class cubedata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)