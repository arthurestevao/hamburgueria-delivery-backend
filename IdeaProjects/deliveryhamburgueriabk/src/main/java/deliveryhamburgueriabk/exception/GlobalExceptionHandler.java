package deliveryhamburgueriabk.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Essa aqui intercepta todas as exceções da aplicação e retorna respostas HTTP padronizadas.
// E também evita que os erros internos vazem para o cliente.

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleNaoEncontrado(RecursoNaoEncontradoException ex) {
        logger.warn("Recurso não encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResposta(404, ex.getMessage()));
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResposta> handleRegraDeNegocio(RegraDeNegocioException ex) {
        logger.warn("Regra de negócio violada: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResposta(400, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResposta(400, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos.");
        logger.warn("Erro de validação: {}", mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResposta(400, mensagem));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleGenerico(Exception ex) {
        logger.error("Erro interno inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResposta(500, "Erro interno no servidor."));
    }
}
