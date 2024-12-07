package org.example.lab4jv.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "author", length = 50)
    private String author;

    @Column(name = "nameb", length = 50)
    private String nameb;

    @Column(name = "year")
    private Integer year;

    @ManyToMany
    @JoinTable(name = "author_libraries",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "libraries_id"))
    private Set<Library> libraries = new LinkedHashSet<>();

}
