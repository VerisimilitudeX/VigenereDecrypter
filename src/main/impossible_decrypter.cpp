// Created by Aryan Agrawal on 2025-01-17 to challenge Piyush Acharya's code :)

#include <bits/stdc++.h>
#include <chrono>
using namespace std;
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
template <class T> using indexedset = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
typedef long long ll;
typedef long double ld;
 
mt19937 rng((uint32_t)chrono::steady_clock::now().time_since_epoch().count());
 
ll getRng(){
    return uniform_int_distribution<ll>(0, 1e18)(rng);
}
 
int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    string s = "THISISLOREMIPSUMIAMSPAMMINGTHISTEXTWITHRANDOMWORDSTOMAKESUREYOUGETTHISONERIGHTTHISISTHEBREAKINGSTRINGFORYOURCODETHISWASWRITTENINSPANISHCLASS";
    string key = "";
    for (int i = 0; i < 50; i++) key += 'A'+(getRng()%26);
    cout << key << "\n";
    string t = "";
    for (int i = 0; i < s.size(); i++){
        t += 'A'+((key[i%key.size()]-'A'+s[i]-'A')%26);
    }
    cout << t << endl;
}