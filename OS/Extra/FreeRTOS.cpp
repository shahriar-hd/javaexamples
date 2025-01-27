/* STM32F4xx Serial FreeRTOS
 * @author Shahriar
 * @date Nov 25, 2024
 */
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "stm32f4xx_hal.h"

#define USARTX USART2
#define USARTX_IRQn USART2_IRQn

uint8_t rx_data;
uint8_t tx_data;

QueueHandle_t xQueue;

void vUARTTask(void *pvParameters) {
    while (1) {
        HAL_UART_Receive(&huart2, &rx_data, 1, HAL_MAX_DELAY);
        xQueueSend(xQueue, &rx_data, portMAX_DELAY);
    }
}

void vProcessTask(void *pvParameters) {
    while (1) {
        xQueueReceive (xQueue, &rx_data, portMAX_DELAY);
        tx_data = rx_data * 2;
    }
}

void vUARTTransmitTask(void *pvParameters) {
    while (1) {
        xQueueReceive (xQueue, &tx_data, portMAX_DELAY);
        HAL_UART_Transmit(&huart2, &tx_data, 1, HAL_MAX_DELAY);
    }
}

int main(void) {
    xQueue = xQueueCreate(10, sizeof(uint8_t));
    xTaskCreate(vUARTTask, "UARTTask", configMINIMAL_STACK_SIZE, NULL, 1, NULL);
    xTaskCreate(vProcessTask, "ProcessTask", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
    xTaskCreate (VUARTTransmitTask, "UARTTransmitTask", configMINIMAL_STACK_SIZE, NULL, 1, NULL);
    vTaskStartScheduler();
    while (1);
}