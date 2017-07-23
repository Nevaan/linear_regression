package com.losek.regression.symbolic.expression

class ExpressionTree {

    boolean isOperator(String c) {
        c in ['+', '-', '*', '/','^']
    }

    String prettyPrint(Node t) {
        t ? """${prettyPrint(t.left)}${t.value}${prettyPrint(t.right)}""" : ""
    }

    Node constructTree(ArrayList<String> postfix) {
        Stack<Node> st = new Stack()
        Node t, t1, t2

        postfix.each { symbol ->
            if(!isOperator(symbol)) {
                t = new Node(symbol)
                st.push(t)
            } else {
                t = new Node(symbol)

                t1 = st.pop()
                t2 = st.pop()

                t.right = t1
                t.left = t2

                st.push(t)
            }
        }
        t = st.peek()
        st.pop()

        t
    }

    Double evaluate(Node tree) {
        if(tree) {
            if (!isOperator(tree.value)) {
                Double.parseDouble(tree.value)
            } else {
                def leftSubtree = evaluate(tree.left)
                def rightSubtree = evaluate(tree.right)
                applyOperator(tree.value, leftSubtree, rightSubtree)
            }
        }
    }

    Double applyOperator(String operator, Double leftVal, Double rightVal) {
        switch(operator) {
            case "+":
                return leftVal + rightVal
            case "-":
                return leftVal - rightVal
            case "*":
                return leftVal * rightVal
            case "/":
                return leftVal / rightVal
            case "^":
                return Math.pow(leftVal,rightVal)
        }
    }

}
