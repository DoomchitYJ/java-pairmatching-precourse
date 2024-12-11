package pairmatching.repository;

import static pairmatching.exception.ExceptionMessage.FILE_PATH_ERROR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.exception.PairmatchingException;

public class CrewNameRepository {

    public List<String> loadCrew(Course course) {
        try {
            Path filePath = Paths.get(course.getFilePath());
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new PairmatchingException(FILE_PATH_ERROR);
        }
    }
}
