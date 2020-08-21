/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.fhnw.ip6.citycourier.ui.effects

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onActive
import androidx.compose.setValue
import androidx.compose.state
import ch.fhnw.ip6.citycourier.data.BlockingFakeTaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.state.UiState
import ch.fhnw.ip6.citycourier.data.Result

/**
 * Effect that interacts with the repository to obtain a task with taskId to display on the screen.
 *
 * To load asynchronous data, effects are better pattern than using @Model classes since
 * effects are Compose lifecycle aware.
 *
 * FIXME: Is it possible to reuse uiStateFrom for this use case?
 */
@Composable
fun fetchTask(taskId: String, taskRequestsRepository: TaskRequestsRepository): UiState<TaskRequest> {

    var taskState: UiState<TaskRequest> by state<UiState<TaskRequest>> { UiState.Loading }

    // Whenever this effect is used in a composable function, it'll load data from the repository
    // when the first composition is applied
    onActive {
        taskRequestsRepository.getTaskRequest(taskId) { result ->
            taskState = when (result) {
                is Result.Success -> {
                    if (result.data != null) {
                        UiState.Success(result.data)
                    } else {
                        UiState.Error(Exception("taskId doesn't exist"))
                    }
                }
                is Result.Error -> UiState.Error(result.exception)
            }
        }
    }

    return taskState
}
