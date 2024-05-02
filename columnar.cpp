// CPP program for illustrating
// Columnar Transposition Cipher
// #include<bits/stdc++.h>
#include<iostream>
#include<map>
using namespace std;

// Key for Columnar Transposition

string key; 
map<int,int> keyMap;

void setPermutationOrder()
{			 
	// Add the permutation order into map 
	for(int i=0; i < key.length(); i++)
	{
        
		keyMap[key[i]] = i;
        // cout<<key[i];
	}
}

// Encryption 
string encryptMessage(string msg)
{
	int row,col,j;
	string cipher = "";
	
	/* calculate column of the matrix*/
	col = key.length(); 
    // cout<<col;
	
	/* calculate Maximum row of the matrix*/
	row = msg.length()/col; 
    // cout<<row;
	
	if (msg.length() % col)
		row += 1;
    // cout<<row;
    

	char matrix[row][col];

	for (int i=0,k=0; i < row; i++)
	{
		for (int j=0; j<col; )
		{
			if(msg[k] == '\0')
			{
				/* Adding the padding character '_' */
				matrix[i][j] = '_';	 
				j++;
			}
			
			if( isalpha(msg[k]) || msg[k]==' ')
			{ 
				/* Adding only space and alphabet into matrix*/
				matrix[i][j] = msg[k];
				j++;
			}
			k++;
		}
	}

    cout<<"Transposition Matrix : "<<endl;

    for(int i=0;i<row;i++){
        cout<<"|";
        for(int j=0;j<col;j++){
            cout<<matrix[i][j];
            cout<<" ";
        }
        cout<<"|";
        cout<<"\n";
    }
    

	for (map<int,int>::iterator ii = keyMap.begin(); ii!=keyMap.end(); ++ii)
	{
		j=ii->second;
		
		// getting cipher text from matrix column wise using permuted key
		for (int i=0; i<row; i++)
		{
			if( isalpha(matrix[i][j]) || matrix[i][j]==' ' || matrix[i][j]=='_')
				cipher += matrix[i][j];
		}
	} 

	return cipher;
}

// Decryption 
string decryptMessage(string cipher)
{
	/* calculate row and column for cipher Matrix */
	int col = key.length();

	int row = cipher.length()/col;
	char cipherMat[row][col];

	/* add character into matrix column wise */
	for (int j=0,k=0; j<col; j++)
		for (int i=0; i<row; i++)
			cipherMat[i][j] = cipher[k++];

	/* update the order of key for decryption */
	int index = 0;
	for( map<int,int>::iterator ii=keyMap.begin(); ii!=keyMap.end(); ++ii)
		ii->second = index++;

	/* Arrange the matrix column wise according 
	to permutation order by adding into new matrix */
	char decCipher[row][col];
	map<int,int>::iterator ii=keyMap.begin();
	int k = 0;
	for (int l=0,j; key[l]!='\0'; k++)
	{
		j = keyMap[key[l++]];
		for (int i=0; i<row; i++)
		{
			decCipher[i][k]=cipherMat[i][j];
		}
	}

	/* getting Message using matrix */
	string msg = "";
	for (int i=0; i<row; i++)
	{
		for(int j=0; j<col; j++)
		{
			if(decCipher[i][j] != '_')
				msg += decCipher[i][j];
		}
	}
	return msg;
}

// Driver Program
int main()
{
	/* message */
	string PlainText;
    cout << "Enter the message : ";
    getline(cin,PlainText);
    cout<<"Entered message : "<<PlainText<<endl;

	/* key */
	cout<<"Enter key : ";
	cin>>key;

	setPermutationOrder();
	
	// Calling encryption function
	string CipherText = encryptMessage(PlainText);
	cout << "Encrypted Message: " << CipherText << endl;
	
	// Calling Decryption function
	cout << "Decrypted Message: " << decryptMessage(CipherText) << endl;

	return 0;
}

// Enter the message : SVPM COE 
// Entered message : SVPM COE 
// Enter key : MAT
// Transposition Matrix : 
// |S V P |
// |M   C |
// |O E   |
// Encrypted Message: V ESMOPC 
// Decrypted Message: SVPM COE
// PS C:\My Files\IS and AI Practicals> 

