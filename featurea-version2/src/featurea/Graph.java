package featurea;

public class Graph {

    public final Vector3[] vectors;
    public int index = 0;
    public double degree = 0;
    public double progress = 0;

    public boolean finished() {
        return vectors[vectors.length-1] == null && index == vectors.length - 2 && progress == 1;
    }

    public Graph(Vector3[] vectors, int index, double degree) {
        this.vectors = vectors;
        this.index = index;
        this.degree = degree;
    }
    
    public Graph copy() {
    	Graph graph = new Graph(vectors, index, degree);
    	graph.progress = this.progress;
        return graph;
    }

    public Vector3 vector() {
        return vectors[index];
    }   

    public Vector3 vector(double shift) {
        Vector3 result = new Vector3(0, 0, 0);
        double percent = progress;
        int i = index;
        int sign = (int) Math.signum(shift);
        double abs = Math.abs(shift);
        if (sign > 0) {
            while (abs > 0 && i < vectors.length) {
                if (vectors[i] == null) {
                    break;
                }
                Vector3 stepShift = new Vector3(vectors[i].dx() * (1 - percent), vectors[i].dy() * (1 - percent), vectors[i].dz() * (1 - percent));
                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                percent = 0;
                i++;
                result.plus(stepShift);
                abs -= stepShift.dlength;
            }
            if (abs > 0 && i >= vectors.length) { // no actions.		
            } else if (abs < 0 && i < vectors.length) {
                abs = Math.abs(abs);
                percent = 1 - (float) (abs / vectors[i - 1].dlength);
                Vector3 stepShift = new Vector3(vectors[i - 1].dx() * (1 - percent), vectors[i - 1].dy() * (1 - percent), vectors[i - 1].dz() * (1 - percent));

                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                result.minus(stepShift);
            } else if (abs < 0 && i >= vectors.length) {
                abs = Math.abs(abs);
                percent = (float) (abs / vectors[vectors.length - 1].dlength);
                Vector3 stepShift = new Vector3(vectors[vectors.length - 1].dx() * (percent), vectors[vectors.length - 1].dy() * (percent), vectors[vectors.length - 1].dz() * (percent));

                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                result.minus(stepShift);
            }
            return result;
        } else if (sign < 0) {
            // Step 1
            while (abs > 0 && i >= 0) {
                Vector3 stepShift = new Vector3(vectors[i].dx() * percent, vectors[i].dy() * percent, vectors[i].dz() * percent);

                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                percent = 1;
                i--;
                result.minus(stepShift);
                abs -= stepShift.dlength;
            }
            // Step 2
            if (abs < 0 && i >= 0) {
                i++;
                abs = Math.abs(abs);
                percent = (float) (abs / vectors[i].dlength);
                Vector3 stepShift = new Vector3(vectors[i].dx() * percent, vectors[i].dy() * percent, vectors[i].dz() * percent);

                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                result.plus(stepShift);
            } else if (abs < 0 && i < 0) {
                abs = Math.abs(abs);
                percent = (float) (abs / vectors[0].dlength);
                Vector3 stepShift = new Vector3(vectors[0].dx() * percent, vectors[0].dy() * percent, vectors[0].dz() * percent);

                // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                result.plus(stepShift);
            } else if (abs > 0 && i < 0) { // no actions.				
            }
            return result;
        }

        return result;
    }

    
    
    public Graph graph(double length) {
        Graph graph = new Graph(this.vectors, this.index, this.degree);
        graph.progress = this.progress;

        // all about 'index' and 'lengthpercent'

        int sign = (int) Math.signum(length);
        double abs = Math.abs(length);

        if (sign > 0) {
            while (abs > 0) {
                if (graph.vector() == null) {  // stop motion throw graph 
                    graph.index = graph.vectors.length - 2;
                    graph.progress = 1;
                    abs = 0; // to avoid overplus evaluation
                    break;
                } else {
                    double k = (1 - graph.progress);
                    Vector3 stepShift = new Vector3(k * graph.vector().dx(), k * graph.vector().dy(), k * graph.vector().dz());

                    // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                    Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                    abs -= stepShift.dlength;
                    graph.index++;
                    if (graph.index >= graph.vectors.length) {
                        graph.index = 0;
                    }
                    graph.progress = 0;
                }
            }
            if (abs < 0) { // go back with overplus: index--; lengthpercent = 1 - overplus/dlength
                graph.index--;
                double overplus = Math.abs(abs);
                graph.progress = 1 - overplus / graph.vector().dlength;
            }
        } else if (sign < 0) {
            while (abs > 0) {
                if (graph.vector() == null) { // stop motion throw graph 
                    graph.index = 0;
                    graph.progress = 0;
                    abs = 0; // to avoid overplus evaluation
                    break;
                } else {
                    Vector3 stepShift = new Vector3(graph.vector().dx() * graph.progress, graph.vector().dy() * graph.progress, graph.vector().dz() * graph.progress);

                    // rotate 'stepShift' on 'degree' relative to centre 'new Vector3(0, 0, 0)'
                    Axis.rotate(new Vector3(0, 0, 0), degree, stepShift);

                    abs -= stepShift.dlength;


                    graph.index--;
                    if (graph.index < 0) {
                        graph.index = graph.vectors.length - 1;
                    }
                    graph.progress = 1;


                }
            }
            if (abs < 0) { // go throw with overplus: index++; lengthpercent = overplus/dlength
                graph.index++;
                if (graph.index > graph.vectors.length - 1) {
                    graph.index = 0;
                }
                double overplus = Math.abs(abs);
                graph.progress = overplus / graph.vector().dlength;

            }
        }

        return graph;
    }

    public String toCode() {
        String string = "public static final Graph yourtitlehere(){\n\treturn new Graph(new Vector3[]{";
        for (Vector3 vector : vectors) {
            if (vector == null) {
                string += "null";
            } else {
                string += "new Vector3(" + vector.dx() + "," + vector.dy() + "," + vector.dz() + "),";
            }
        }
        string += "}, 0, 0);}";
        return string;
    }
    
    public String toString() {
        String vs = "";
        for (Vector3 vector : vectors) {
            if (vector == null) {
                vs += "null";
            } else {
                vs += "(" + vector.dx() + "," + vector.dy() + "," + vector.dz() + "), ";
            }
        }
        return "[" + vs + "]," + index + "," + degree + "," + progress;
    }
}