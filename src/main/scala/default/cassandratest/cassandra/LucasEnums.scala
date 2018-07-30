/*
 * Copyright 2018 lre
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package default.cassandratest.cassandra

import enumeratum._

import scala.collection.immutable

object LucasEnums {

  sealed abstract class LucasEntity extends EnumEntry {
    val table: String
  }

  object LucasEntities extends Enum[LucasEntity] with CirceEnum[LucasEntity] {
    override def values: immutable.IndexedSeq[LucasEntity] = findValues

    case object Addresses extends LucasEntity {
      val table: String = "addresses"
    }

    case object Hobbies extends LucasEntity {
      val table: String = "hobbies"
    }
  }

}
