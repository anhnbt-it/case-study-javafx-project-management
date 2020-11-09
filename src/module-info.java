/**
 * Created by IntelliJ IDEA.
 * User: AnhNBT (anhnbt.it@gmail.com)
 * Date: 08/11/2020
 * Time: 5:42 CH
 */
module javafx.project.management {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.anhnbt to javafx.fxml;
    exports com.anhnbt;
}