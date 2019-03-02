PATH_TO_FX = javafx-sdk-11.0.2/lib
FLAGS = --module-path $(PATH_TO_FX) --add-modules=javafx.controls
Interface = Interface.java
EditTable = EditTable.java Table.java Column.java
Table = Table.java Records.java
Column = Column.java
Records = Records.java
File = File.java Table.java

%: %.java
	javac $(FLAGS) $($@)
	java $(FLAGS) -ea $@