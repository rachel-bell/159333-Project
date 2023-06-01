//test case 1：
//dry food
//20kg dog
//protein 16
//fat 7
//ash 5
//fibre 2.6
//
//final result: 380
//
//
//test case 2：
//wet food
//20kg dog
//protein 6
//fat 6
//ash 5
//fibre 0.7
//
//final result: 1260


//Calculate_CHO(food_type, protein_rate, float fat_rate, float ash_rate, float fibre_rate) {
//	// 这里需要按照下面的公式更新输入的几个值，后边的计算会使用更新后的值
//	if (food_type == dry)
//	{
//		protein_rate = protein_rate / 0.9;   6.666666
//		fat_rate = fat_rate / 0.9;      6.666666
//		ash_rate = ash_rate / 0.9;     5.55555555
//		fibre_rate = fibre_rate / 0.9;    0.77777777
//	}
//	else
//	{
//		protein_rate = protein_rate / 0.25;
//		fat_rate = fat_rate / 0.25;
//		ash_rate = ash_rate / 0.25;
//		fibre_rate = fibre_rate / 0.25;
//	}
//
//	CHO_rate = 100 - protein_rate - fat_rate - ash_rate - fibre_rate;
//
//	return CHO_rate;
//}
//
//Pet_need_per_day(weight, pet_type) {
//	if (pet_type=dog)
//		return weight^0.75 * 130;
//	else
//		return weight^0.67 * 100;
//}
//
//Offer_per_100g() {
//	energy_protein = protein_rate * 3.5;
//	energy_fat = fat_rate * 8.5;
//	energy_CHO = CHO_rate * 3.5;
//	return energy_protein + energy_fat + energy_CHO;
//}
//
//Food_need_per_day(weight, pet_type) {
//	pet_need_per_day = Pet_need_per_day(weight, pet_type);
//	offer_per_g = Offer_per_100g() / 100;
//	DM_food = pet_need_per_day / offer_per_g;
//	if (food_type == dry)
//		return DM_food * 100 / 90;
//	else
//		return DM_food * 100 / 25;
//}
