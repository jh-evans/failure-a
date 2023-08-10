package org.darien.tools.codegen.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.darien.tools.codegen.CodeGen;
import org.darien.tools.codegen.Main;

public class Tests {
    @Test
    void failure_utils_oneisnull_args_is_null_test() {
    	Main m = new Main();
    	CodeGen cg = m.generate("org.darien.tools.codegen.tests.TestCodeGen", false);
    	System.out.println(cg);
    }
}