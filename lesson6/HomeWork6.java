package hmJava3.hm6HomeWork;


public class HomeWork6 {

    public int[] method1(int[] arr){

        boolean check4 = false;
        int index = 0;

        for(int i=0;i<arr.length;i++){
            if(arr[i]==4){
                check4=true;
                index = i;
            }
        }

        if(!check4){
            throw new RuntimeException();
        }

        int[] arr2 = new int[arr.length-(index+1)];
        for(int i=index+1,j=0;i<arr.length;i++,j++){
            arr2[j]=arr[i];
        }

        return arr2;

    }

    public boolean method2(int[] arr){
        boolean one = false;
        boolean four = false;

        for(int i=0;i<arr.length;i++){
            if(arr[i]!=1&&arr[i]!=4){
                return false;
            }else if(arr[i]==1&&!one){
                one=true;
            }else if(arr[i]==4&&!four){
                four=true;
            }
        }

        return one&&four;
    }

}
