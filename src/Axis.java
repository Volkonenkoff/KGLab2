public class Axis {
    Dot A;
    Dot B;
    Dot originA;
    Dot originB;
    Axis(Dot A,Dot B)
    {

        this.A=A;
        this.B=B;
        this.originA=new Dot(this.A.x,this.A.y,this.A.z);
        this.originB=new Dot(this.B.x,this.B.y,this.B.z);

    }
    public void rotation(){
        double rot[][]={
                {
                        0.707107, 0.408248, -0.577353,0
                },
                {
                        0, 0.816497, 0.577353,0
                },
                {
                        0.707107,-0.408248,0.577353,0
                },
                {
                        0,0,0,1
                }
        };
        double exp[][]={
                {
                        A.x, A.y, A.z, 1
                },
                {
                        B.x,B.y,B.z,1
                }

        };
        double res[][]=new double[2][4];
        for (int i=0;i<exp.length;i++)
        {
            for(int j=0; j<rot[0].length;j++)
            {
                res[i][j]=0;
                for(int k=0; k<rot[0].length;k++)
                {
                    res[i][j]=res[i][j]+exp[i][k]*rot[k][j];
                }
            }

        }

        A.x=res[0][0];
        A.y=res[0][1];
        A.z=res[0][2];
        B.x=res[1][0];
        B.y=res[1][1];
        B.z=res[1][2];


    }
    public void projection()
    {
        double rot[][]={
                {
                        1,0,0,0
                },
                {
                        0,1,0,0
                },
                {
                        0,0,0,0
                },
                {
                        0,0,0,1
                }
        };
        double exp[][]={
                {
                        A.x, A.y, A.z, 1
                },
                {
                        B.x,B.y,B.z,1
                }
        };
        double res[][]=new double[2][4];
        for (int i=0;i<exp.length;i++)
        {
            for(int j=0; j<rot[0].length;j++)
            {
                res[i][j]=0;
                for(int k=0; k<rot[0].length;k++)
                {
                    res[i][j]=res[i][j]+exp[i][k]*rot[k][j];
                }
            }

        }
        A.x=res[0][0];
        A.y=res[0][1];
        A.z=res[0][2];
        B.x=res[1][0];
        B.y=res[1][1];
        B.z=res[1][2];

    }
}
