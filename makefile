PATH_TO_FX = javafx-sdk-11.0.2/lib
FLAGS = --module-path $(PATH_TO_FX) --add-modules=javafx.controls
Interface = Interface.java Table.java Records.java InputTableName.java
Table = Table.java Records.java
Records = Records.java
File = File.java Table.java
Databases = Databases.java Table.java Records.java
InputTableName = InputTableName.java

%: %.java
	javac $(FLAGS) $($@)
	java $(FLAGS) -ea $@