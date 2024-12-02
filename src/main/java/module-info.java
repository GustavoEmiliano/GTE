
module gte.br.gte3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens gte.br.gte3 to javafx.fxml;
    exports gte.br.gte3;
    exports gte.br.gte3.Util;
    opens gte.br.gte3.Util to javafx.fxml;
    opens gte.br.gte3.Model to org.hibernate.orm.core, javafx.base;
    opens gte.br.gte3.Controllers to javafx.fxml;
}
