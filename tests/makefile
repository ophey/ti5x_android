
TESTS = test1 test2

TESTS_JAVA = $(TESTS:%=%.java)
TESTS_CLASS = $(TESTS:%=%.class)
TESTS_RES = $(TESTS:%=%.res)

all: compile run check

compile: $(TESTS_CLASS)

$(TESTS_CLASS): $(TEST_JAVA)

%.class: %.java
	javac -classpath ../bin/classes $(@:%.class=%.java)

run:  $(TESTS)

$(TESTS): $(TEST_CLASS)
	java -classpath ../bin/classes:. $@ > $@.res

check: $(TESTS_RES)

$(TESTS_RES): force
	@echo "=============================== " $(@:%.res=%)
	-diff -c $(@:%.res=%.out) $@

clean:
	rm -f *.class *.res

force:
