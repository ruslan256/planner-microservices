package org.ruslan.todo.mc.users.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserSearchValues {

    // fields of search
    private String email;
    private String username;

    // pagination
    private Integer pageNumber;
    private Integer pageSize;

    // sorting
    private String sortColumn;
    private String sortDirection;

}