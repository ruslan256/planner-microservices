package org.ruslan.todo.mc.todo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchValues {

    // fields of search
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;
    private Long userId;

    private Date dateFrom;
    private Date dateTo;

    // pagination
    private Integer pageNumber;
    private Integer pageSize;

    // sorting
    private String sortColumn;
    private String sortDirection;

}