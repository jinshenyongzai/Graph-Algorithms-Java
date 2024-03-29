import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionBFS {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetectionBFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            pre[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(bfs(v)){
                    hasCycle = true;
                    break;
                }
    }

    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
                else if(pre[v] != w)
                    return true;
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        CycleDetectionBFS cycleDetectionBFS = new CycleDetectionBFS(g);
        System.out.println(cycleDetectionBFS.hasCycle());

        Graph g2 = new Graph("g2.txt");
        CycleDetectionBFS cycleDetectionBFS2 = new CycleDetectionBFS(g2);
        System.out.println(cycleDetectionBFS2.hasCycle());
    }
}