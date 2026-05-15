package com.example.project1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Data class representing the state of the simulation.
 * @param progress Current progress of the vehicle along the route (0.0 to 1.0).
 * @param speed Speed of the vehicle in units per second.
 * @param eta Estimated time of arrival in seconds.
 */
data class SimulationState(
    val progress: Float = 0f,
    val speed: Float = 0.05f, // progress units per second
    val eta: Int = 0
)

class YatriMitraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SimulationState())
    val uiState: StateFlow<SimulationState> = _uiState.asStateFlow()

    // The total length of the route is considered 1.0 (100%)
    // The user's stop is at the end of the route (1.0)
    private val userStopPosition = 1.0f

    init {
        startSimulation()
    }

    /**
     * Simulation Logic:
     * Updates the vehicle's position and calculates the ETA in real-time.
     */
    private fun startSimulation() {
        viewModelScope.launch {
            while (true) {
                _uiState.update { currentState ->
                    val newProgress = (currentState.progress + (currentState.speed * 0.1f)) % 1.1f
                    
                    // Math Logic: ETA = Distance / Speed
                    // Remaining Distance = User Stop Position - Current Progress
                    val remainingDistance = (userStopPosition - newProgress).coerceAtLeast(0f)
                    val newEta = (remainingDistance / currentState.speed).toInt()

                    currentState.copy(
                        progress = if (newProgress > 1.0f) 0f else newProgress,
                        eta = newEta
                    )
                }
                // Update every 100ms for smooth movement
                delay(100)
            }
        }
    }
}