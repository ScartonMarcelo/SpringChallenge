package exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException{
	//public ResourceNotFoundException(String msg) {
		//super(msg);
	//}

	private String title;
	private Integer httpCode;
	private LocalDate timeStamp;
	private String message;
}
