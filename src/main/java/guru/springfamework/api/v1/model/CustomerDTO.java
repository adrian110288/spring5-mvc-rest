package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "This is a model", description = "This is a mode description")
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstname;
    private String lastname;
}
