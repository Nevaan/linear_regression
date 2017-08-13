package com.losek.regression.symbolic.expression

import com.losek.regression.symbolic.genetics.Chromosome


// TODO: add unit test
class ExpressionGenerator {

    // TODO: extract it
    final static operatorList = ['+', '-', '*', '/','^']

    static Node generateNode(int maxDepth, int currentDepth = 1) {
        def chance = Math.random()
        def random = new Random()
        Node node = null

        if(currentDepth==1){
            node = new Node(operatorList[random.nextInt(operatorList.size())])
            node.left = generateNode(maxDepth, currentDepth+1)
            node.right = generateNode(maxDepth, currentDepth+1)
            return node
        }

        // TODO: configurable chance
        if(chance > 0.5 && currentDepth != maxDepth) {
            node = new Node(operatorList[random.nextInt(operatorList.size())])
            node.left = generateNode(maxDepth, currentDepth + 1)
            node.right = generateNode(maxDepth, currentDepth + 1)
        } else {
            // TODO: configurable range
            if(Math.random() < 0.5) {
                node = new Node(-200 + random.nextDouble() * 200 as String)
            } else {
                node = new Node("x" as String)
            }
        }

        return node
    }
}
