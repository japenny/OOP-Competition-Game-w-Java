import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTester {

    @Test
    public void testInDepthFirst() {
        char [][]maze =
                {
                {'_','X','p','X'},
                {'_','_','_','_'},
                {'X','_','X','P'},
                {'s','_','X','X'},
                {'X','_','E','X'}
                };
        Maze test = new Maze(maze);
        int [] arr = test.inDepthFirst();
        assertEquals(80,arr[0]);
        assertEquals(12,arr[1]);


        char [][]maze1 =
                {
                        {'_','_','_','P'},
                        {'X','X','_','X'},
                        {'_','_','_','_'},
                        {'E','X','X','p'},
                        {'_','_','s','X'}
                };
        Maze test1 = new Maze(maze1);
        int [] arr1 = test1.inDepthFirst();
        assertEquals(75,arr1[0]);
        assertEquals(11,arr1[1]);



        char [][]maze2 =
                {
                        {'_','_','_','s'},
                        {'X','X','X','_'},
                        {'_','P','_','_'},
                        {'_','X','X','_'},
                        {'E','_','p','_'}
                };
        Maze test2 = new Maze(maze2);
        int [] arr2 = test2.inDepthFirst();
        assertEquals(70,arr2[0]);
        assertEquals(11,arr2[1]);

    }




    @Test
    public void testByLevel() {
        char [][]maze =
                {
                        {'_','X','p','X'},
                        {'_','_','_','_'},
                        {'X','_','X','E'},

                };
        Maze test = new Maze(maze);
        int [] arr = test.byLevel();
        assertEquals(60,arr[0]);
        assertEquals(8,arr[1]);

        char [][]maze1 =
                {
                        {'_','X','p','X'},
                        {'_','_','_','_'},
                        {'X','_','X','P'},
                        {'s','_','X','X'},
                        {'X','_','E','X'}

                };
        Maze test1 = new Maze(maze1);
        int [] arr1 = test1.byLevel();
        assertEquals(80,arr1[0]);
        assertEquals(12,arr1[1]);

        char [][]maze2 =
                {
                        {'_','_','_','s'},
                        {'X','X','X','_'},
                        {'_','P','_','_'},
                        {'_','X','X','_'},
                        {'E','_','p','_'}

                };
        Maze test2 = new Maze(maze2);
        int [] arr2 = test2.byLevel();
        assertEquals(80,arr2[0]);
        assertEquals(15,arr2[1]);

    }

    @Test
    public void testQScompetition() {
        char [][]maze =
                {
                        {'_','_','_'},
                        {'_','X','P'},
                        {'E','X','P'}
                };
        Maze test = new Maze(maze);
        test.QScompetition();
        assertEquals("Stack Wins :)", test.SQwin());


        char [][]maze1 =
                {
                        {'_','X','s','X'},
                        {'_','_','_','_'},
                        {'_','X','X','_'},
                        {'P','X','E','_'},
                };
        Maze test1 = new Maze(maze1);
        test1.QScompetition();
        assertEquals("Queue Wins :)", test1.SQwin());


        char [][]maze2 =
                {
                        {'_','_','_','-','_','_','X','P','_','_','_'},
                        {'_','X','_','X','X','_','X','X','X','X','_'},
                        {'_','X','_','p','X','s','_','_','_','_','s'},
                        {'_','X','X','X','X','_','X','_','X','X','X'},
                        {'_','_','_','_','_','_','X','_','_','_','_'},
                        {'X','X','_','X','_','X','X','X','X','_','X'},
                        {'_','_','_','X','_','X','P','X','P','_','_'},
                        {'s','X','X','X','_','X','P','X','_','X','_'},
                        {'_','_','_','_','_','X','_','X','_','X','s'},
                        {'_','X','P','X','_','X','_','X','_','X','_'},
                        {'_','_','_','X','_','_','_','X','E','X','E'}

                };
        Maze test2 = new Maze(maze2);
        test2.QScompetition();
        assertEquals("Queue Wins :)", test2.SQwin());

    }
}