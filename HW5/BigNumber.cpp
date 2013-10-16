#include <iostream>
#include <string>
#include <vector>
using namespace std;

class BigDecimal{
	private:
		bool NaN;
		vector<char> decimal;

		char add(char d1, char d2, char carry) const{
			int result = d1 - 48 + d2 - 48 + carry - 48;
			if(result >= 10){
				result -= 10;
			}
			return result + 48;
		}
	public:
		BigDecimal(string const& input){
			NaN = false;
			if(input.compare("NaN") == 0){
				NaN = true;
				return;
			}
			for(int i = 0; i < input.length(); i++){
				decimal.push_back(input[i]);
			}
			if(input.find('.') == string::npos){
				decimal.push_back('.');
			}
			/*for(int i = 0; i < decimal.size(); i++){
				cout << decimal[i];
			}
			cout << endl;*/
		}

        bool getNaN() const{
            return NaN;
        }

		BigDecimal operator+(const BigDecimal& n) const{
			string result = "", num1_ones, num2_ones, num1_dec, num2_dec;
			char carry = '0', out;
			n.getString();
			if(NaN || n.getNaN()){
				return BigDecimal("NaN");
			}
			num1_dec = this->getString().substr(this->getString().find('.') + 1, this->getString().length());
			num2_dec = n.getString().substr(n.getString().find('.') + 1, n.getString().length());
			num1_ones = this->getString().substr(0, this->getString().find('.'));
			num2_ones = n.getString().substr(0, n.getString().find('.'));
			while(num1_ones.length() < num2_ones.length()){
				num1_ones = '0' + num1_ones;
			}
			while(num1_ones.length() > num2_ones.length()){
				num2_ones = '0' + num2_ones;
			}
			while(num1_dec.length() < num2_dec.length()){
				num1_dec = num1_dec + '0';
			}
			while(num1_dec.length() > num2_dec.length()){
				num2_dec = num2_dec + '0';
			}
			/*cout << num1_ones <<endl;
			cout << num1_dec <<endl;
			cout << num2_ones <<endl;
			cout << num2_dec <<endl;*/
			for(int i = num1_dec.length() - 1; i >= 0; i--){
				out = add(num1_dec[i], num2_dec[i], carry);
				result = out + result;
				if(out >= num1_dec[i] && out >= num2_dec[i] && out >= carry){
					carry = '0';
				}else{
					carry = '1';
				}
			}
			result = '.' + result;
			for(int i = num1_ones.length() - 1; i >= 0; i--){
                out = add(num1_ones[i], num2_ones[i], carry);
                result = out + result;
                if(out >= num1_ones[i] && out >= num2_ones[i] && out >= carry)){
                    carry = '0';
                }else{
                    carry = '1';
                }
			}
            if(carry == '1'){
                result = '1' + result;
            }
            while(result[result.length() - 1] == '0'){
                result = result.substr(0, result.length()-1);
            }
            return BigDecimal(result);
        }

        BigDecimal operator/(const BigDecimal& n) const{
            if(n.getString() == "1."){
                return BigDecimal(getString());
            } else {
                return BigDecimal("NaN");
            }
        }


		string getString() const{
			string out = "";
			if(NaN){
				return "NaN";
			}
			for(int i = 0; i < decimal.size(); i++){
				out = out + decimal[i];
			}
			return out;
		}

		friend ostream & operator<<( ostream & os, const BigDecimal& big);
};

ostream & operator<<(ostream & os, const BigDecimal& big){
    os << big.getString();
    return os;
}

int main(){
	BigDecimal big("5.123123");
	BigDecimal big2("1123123.12312");
	BigDecimal out = big + big2;
	cout << out << endl;
	//cout << out.getString() <<endl;
	return 0;
}



