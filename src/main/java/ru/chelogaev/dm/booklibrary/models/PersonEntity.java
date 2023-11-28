package ru.chelogaev.dm.booklibrary.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "public", catalog = "booklibrary")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Size(max = 100, message = "The \"FIO\" field the field must contain no more than 100 characters")
    @NotEmpty(message = "The \"FIO\" field must be not empty")
    private String fio;

    @Column(name = "email")
    @Size(max = 50, message = "The \"email\" field the field must contain no more than 50 characters")
    @Email(message = "Email is not valid")
    private String email;
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "Enter an integer")
    @NotEmpty(message = "The \"birth year\" field must be not empty")
    @Column(name = "birth_year", length = 4)
    private String birthYear;
    @OneToMany(mappedBy = "owner")
    private List<BookEntity> books;

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return id == that.id && Objects.equals(fio, that.fio) && Objects.equals(email, that.email) && Objects.equals(birthYear, that.birthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, email, birthYear);
    }
}
