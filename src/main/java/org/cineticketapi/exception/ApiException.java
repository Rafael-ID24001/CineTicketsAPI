package org.cineticketapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para el manejo de errores en la API.
 * <p>
 * Esta clase extiende {@link RuntimeException} y permite especificar el código HTTP
 * de respuesta y datos adicionales asociados al error.
 * </p>
 */
public class ApiException extends RuntimeException {

  /**
   * Código HTTP de estado asociado a la excepción.
   * <p>
   * Define el código de respuesta HTTP que se enviará al cliente cuando
   * esta excepción sea capturada por el manejador de excepciones.
   * </p>
   */
  private final HttpStatus status;

  /**
   * Datos adicionales asociados al error.
   * <p>
   * Puede contener información específica sobre el error, como campos
   * inválidos, valores esperados, o cualquier otro dato relevante para
   * el diagnóstico del problema. Marcado como {@code transient} para
   * excluirlo de la serialización.
   * </p>
   */
  private final transient Object data;

  /**
   * Construye una nueva {@code ApiException} con el estado HTTP y mensaje especificados.
   * <p>
   * Los datos adicionales se inicializan como {@code null}.
   * </p>
   *
   * @param status  el código HTTP de estado asociado al error
   * @param message el mensaje descriptivo del error
   * @throws IllegalArgumentException si {@code status} es {@code null}
   * @see org.springframework.http.HttpStatus
   */
  public ApiException(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.data = null;
  }

  /**
   * Construye una nueva {@code ApiException} con estado HTTP, mensaje y datos adicionales.
   * <p>
   * Permite incluir información específica sobre el error que puede ser útil
   * para el cliente al diagnosticar el problema.
   * </p>
   *
   * @param status  el código HTTP de estado asociado al error
   * @param message el mensaje descriptivo del error
   * @param data    datos adicionales asociados al error, pueden ser {@code null}
   * @throws IllegalArgumentException si {@code status} es {@code null}
   * @see org.springframework.http.HttpStatus
   */
  public ApiException(HttpStatus status, String message, Object data) {
    super(message);
    this.status = status;
    this.data = data;
  }

  /**
   * Retorna el código HTTP de estado asociado a esta excepción.
   *
   * @return el código HTTP de estado
   * @see org.springframework.http.HttpStatus
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * Retorna los datos adicionales asociados al error.
   * <p>
   * Puede contener información específica sobre el error o {@code null}
   * si no hay datos adicionales.
   * </p>
   *
   * @return los datos adicionales del error, o {@code null} si no existen
   */
  public Object getData() {
    return data;
  }

  /**
   * Indica si esta excepción contiene datos adicionales.
   *
   * @return {@code true} si hay datos adicionales, {@code false} en caso contrario
   */
  public boolean hasData() {
    return data != null;
  }

  /**
   * Retorna una representación en formato String de esta excepción.
   * <p>
   * Incluye el estado HTTP, mensaje e información sobre la presencia de datos adicionales.
   * </p>
   *
   * @return una representación en String de esta excepción
   */
  @Override
  public String toString() {
    return String.format("ApiException{status=%s, message='%s', hasData=%s}",
            status, getMessage(), hasData());
  }
}