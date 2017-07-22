package com.losek.regression.symbolic.expression

import org.junit.Before

class ExpressionTreeTest extends GroovyTestCase {

    def tree

    @Before
    //before each test
    void setUp() {
        tree = new ExpressionTree()
    }

    void testIsOperator() {

        def operators = ['+', '-', '*', '/', '^']
        def symbols = ['4', 'e', '54', '43.4', '%']

        operators.each { operator ->
            assertTrue(tree.isOperator(operator) as boolean)
        }

        symbols.each {symbol ->
            assertFalse(tree.isOperator(symbol) as boolean)
        }

    }

    void testInorder() {
        Node root = new Node('+')
        Node left1 = new Node('*')
        Node right1 = new Node('4')
        Node left21 = new Node('10')
        Node left22 = new Node('3')

        root.left = left1
        root.right = right1
        left1.left = left21
        left1.right = left22

        def result = tree.inorder(root)
        assert result as String == '10*3+4'
    }

    void testConstructTree() {
        def expression = ['a', 'b', '+', 'e', 'f', '*', 'g', '*','-']
        assert 'a+b-e*f*g' == tree.inorder(tree.constructTree(expression))
    }
}
